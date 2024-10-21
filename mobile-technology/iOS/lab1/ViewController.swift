import UIKit
import AVFoundation

class ViewController: UIViewController {
    let sound = Bundle.main.url(forResource: "win", withExtension: "mp3")
    let sound1 = Bundle.main.url(forResource: "restart", withExtension: "mp3")
    let sound2 = Bundle.main.url(forResource: "nice_shot", withExtension: "mp3")
    var audioPlayer = AVAudioPlayer()
    
    @IBOutlet weak var slider: UISlider!
    @IBOutlet weak var targetLabel: UILabel!
    @IBOutlet weak var scoreLabel: UILabel!
    @IBOutlet weak var roundLabel: UILabel!
    
    var targetValue = 0
    var currentScore = 0
    var round = 0
    let maxRounds = 10
    
    
    
    //    alert.addAction(action)
    //    present(alert, animated: true, completion: nil)
    //    @IBOutlet weak var string1: UITextField!
    //    @IBOutlet weak var string2: UITextField!
    //    @IBOutlet weak var wynik: UILabel!
    //
    //    @IBAction func prownaj(_ sender: UIButton) {
    //        let text1 = string1.text ?? ""
    //        let text2 = string2.text ?? ""
    //
    //        if(text1 == text2) {
    //            wynik.text = "Stringi sa takie same"
    //            print("takie same")
    //
    //        } else {
    //            wynik.text = "Stringi sa rozne"
    //            print("ROZNE")
    //        }
    //
    //    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let thumbImageNormal = UIImage(named: "SliderIcon.png")!
        slider.setThumbImage(thumbImageNormal, for: .normal)
        
        let insets = UIEdgeInsets( top: 0,
                                  left: 14,
                                  bottom: 0,
                                  right: 14)
        let trackLeftImage = UIImage(named: "SliderLeft.png")!
        let trackLeftResizable = trackLeftImage.resizableImage(withCapInsets: insets)
        slider.setMinimumTrackImage(trackLeftResizable, for: .normal)
        
        let trackRightImage = UIImage(named: "SliderRight.png")!
        let trackRightResizable = trackRightImage.resizableImage(withCapInsets: insets)
        slider.setMinimumTrackImage(trackRightResizable, for: .normal)
        
        startNewGame()
    }
    
    @IBAction func resignKeyboard(_ sender: UITextField!){
        sender.resignFirstResponder()
    }
    
    func startNewGame(){
        currentScore = 0
        round = 0
        startNewRound()
    }
    
    func startNewRound(){
        round += 1
        targetValue = Int.random(in: 1...100)
        targetLabel.text = String(targetValue)
        updateLabels()
    }
    
    func updateLabels(){
        scoreLabel.text = String(currentScore)
        roundLabel.text = String(round)
    }
    
    @IBAction func checkValue(_ sender: UIButton) {
        let sliderValue = Int(slider.value.rounded())
        let diff = abs(targetValue - sliderValue)
        
        let points = 100 - diff
        
        if(points == 100) {
            do {
                audioPlayer = try AVAudioPlayer(contentsOf: sound2!)
                audioPlayer.play()
            } catch {
                print("nie udalo sie wladowac pliku dzwieku")
            }
        }
        
        currentScore += points
        
        //alert
        let alert = UIAlertController(title: "Wynik", message: "Uzyskane punkty \(points)", preferredStyle: .alert)
        let action = UIAlertAction(title: "Graj dalej", style: .default) { _ in
            if (self.round < self.maxRounds){
                self.startNewRound()
            }else{
                self.endGame()
            }
        }
        
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
    }
    
    func endGame(){
        let alert = UIAlertController(title: "Koniec gry!", message: "Uzyskane punkty \(currentScore)", preferredStyle: .alert)
        let action = UIAlertAction(title: "Zagraj ponownie", style: .default) { _ in
            self.startNewGame()
        }
        
        alert.addAction(action)
        present(alert, animated: true, completion: nil)
        
        do {
            audioPlayer = try AVAudioPlayer(contentsOf: sound!)
            audioPlayer.play()
        } catch {
            print("nie udalo sie wladowac pliku dzwieku")
        }
    }
    
    
    @IBAction func restartGame(_ sender: UIButton) {
        startNewGame()
        do {
            audioPlayer = try AVAudioPlayer(contentsOf: sound1!)
            audioPlayer.play()
        } catch {
            print("nie udalo sie wladowac pliku dzwieku")
        }
    }
    
}

